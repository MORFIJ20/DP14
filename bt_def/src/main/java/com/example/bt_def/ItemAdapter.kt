package com.example.bt_def

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bt_def.databinding.ListItemBinding

class ItemAdapter (private val listener: Listener, val adapterType: Boolean):
    ListAdapter <Listitem, ItemAdapter.MyHolder>(Comparator()){
    private var oldCheckBoc: CheckBox? = null
    class MyHolder(view: View, private val adapter: ItemAdapter,
    private val listener: Listener, val adapterType: Boolean):
        RecyclerView.ViewHolder(view){
        private val b = ListItemBinding.bind(view)
        private var item1: Listitem? = null
        init {
            b.checkBox.setOnClickListener {
                item1?.let { it1 -> listener.onClick(it1) }
            adapter.selectCheckBox(it as CheckBox)
            }
            itemView.setOnClickListener {
                if (adapterType){
                    try {
                        item1?.device?.createBond()
                    } catch (e: SecurityException){}
                } else{
                    item1?.let { it1 -> listener.onClick(it1) }
                    adapter.selectCheckBox(b.checkBox)
                }

            }
        }
        fun bind(item: Listitem) = with(b){
            checkBox.visibility = if (adapterType)  View.GONE else View.VISIBLE
            item1 = item
            try {
                name.text= item.device.name
                mac.text=item.device.address
            } catch (e: SecurityException){

            }

            if (item.isChecked) adapter.selectCheckBox(checkBox)
        }
    }
    class Comparator: DiffUtil.ItemCallback<Listitem>(){
        override fun areItemsTheSame(oldItem: Listitem, newItem: Listitem): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Listitem, newItem: Listitem): Boolean {
            return  oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view, this, listener, adapterType)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun selectCheckBox( checkBox: CheckBox){
        oldCheckBoc?.isChecked = false
        oldCheckBoc = checkBox
        oldCheckBoc?.isChecked = true
    }
    interface Listener {
        fun onClick(device: Listitem)
    }
}