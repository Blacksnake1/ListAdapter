package com.thanhviet.smartlistadapter

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapter.Note
import com.example.listadapter.NoteDiffCallBack
import com.example.listadapter.R

/**
 * Created by FRAMGIA\bui.dinh.viet on 27/03/2018.
 */
class NoteAdapter(
    val clickListener: (Int) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteVH>(NoteDiffCallBack()) {
  override fun onBindViewHolder(holder: NoteVH, @SuppressLint("RecyclerView") position: Int) {
    holder.bindData(getItem(position), clickListener)
//    holder.title.text = getItem(position).title
//    var mLastClickTime: Long = 0
//    holder.trash.setOnClickListener(object : View.OnClickListener {
//      override fun onClick(view: View) {
//        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000)
//          return
//        clickListener(position)
//        mLastClickTime = SystemClock.elapsedRealtime()
//
//      }
//    })
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
    val inflater = LayoutInflater.from(parent.context)
    return NoteVH(inflater.inflate(R.layout.item_note, parent, false))
  }

  class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val trash: ImageView = itemView.findViewById(R.id.btn_delete)

    fun bindData(note: Note, clickListener: (Int) -> Unit) {
      title.text = note.title
      var mLastClickTime: Long = 0
      trash.setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
          if (SystemClock.elapsedRealtime() - mLastClickTime < 1000)
            return

          clickListener(adapterPosition)
          mLastClickTime = SystemClock.elapsedRealtime()

        }
      })
    }
  }
}