package com.example.coinproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.coinproject.databinding.RecyclerRowBinding
import com.example.coinproject.model.Coin
import com.example.coinproject.util.doPlaceHolder
import com.example.coinproject.util.downloadImage
import com.example.coinproject.view.ListFragmentDirections

class CoinRecyclerAdapter(val coinList : ArrayList<Coin>) : RecyclerView.Adapter<CoinRecyclerAdapter.CoinViewHolder>() {

    class CoinViewHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
       val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    fun updateCoinList(newCoinList : List<Coin>) {
        coinList.clear()
        coinList.addAll(newCoinList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.binding.name.text = coinList[position].name
        holder.binding.sybmol.text = coinList[position].symbol

        holder.itemView.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToDetaiFragment(coinList[position].uuid.toString())
            Navigation.findNavController(it).navigate(action)
        }

        coinList[position].image?.let {
            holder.binding.imageView.downloadImage(it, doPlaceHolder(holder.itemView.context))
        }

    }
}