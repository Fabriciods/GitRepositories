package br.com.fao.gitshare.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.fao.gitshare.data.model.Repo
import br.com.fao.gitshare.databinding.ItemRepoBinding
import com.bumptech.glide.Glide

class RepoListAdapter : ListAdapter<Repo, RepoListAdapter.ViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Repo){
            binding.tvRepoName.text = item.name
            binding.tvRepoDescription.text = item.description
            binding.tvRepoLanguage.text = item.language
            binding.chipStar.text = item.stargazersCount.toString()

            Glide.with(binding.root.context).load(item.owner.avatarURL).into(binding.ivOwner)
        }
    }



}
class DiffCallBack : DiffUtil.ItemCallback<Repo>(){
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id

}