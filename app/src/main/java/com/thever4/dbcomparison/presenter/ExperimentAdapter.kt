package com.thever4.dbcomparison.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thever4.dbcomparison.R
import com.thever4.dbcomparison.data.model.ExperimentItem
import com.thever4.dbcomparison.databinding.ExperimentItemBinding

class ExperimentAdapter :
    ListAdapter<ExperimentItem, ExperimentAdapter.ExperimentViewHolder>(ExperimentDiffUtil()) {

    class ExperimentViewHolder(
        private val binding: ExperimentItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExperimentItem) = with(binding) {
            val context = binding.root.context
            experimentDescription.text = when (context.resources.getResourceTypeName(item.description)) {
                "string" -> context.getString(item.description, *item.arguments.toTypedArray())
                "plurals" -> context.resources.getQuantityString(item.description, item.arguments.first() as Int, *item.arguments.toTypedArray())
                else -> context.getString(item.description)
            }
            experimentElapsed.text = context.getString(item.unit, item.elapsed)
        }

    }

    class ExperimentDiffUtil : DiffUtil.ItemCallback<ExperimentItem>() {
        override fun areItemsTheSame(
            oldItem: ExperimentItem,
            newItem: ExperimentItem
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ExperimentItem,
            newItem: ExperimentItem
        ): Boolean =
            oldItem.description == newItem.description && oldItem.elapsed == newItem.elapsed

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperimentViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ExperimentItemBinding.inflate(inflater, parent, false)
        return ExperimentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExperimentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}