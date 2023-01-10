package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import ru.netology.nmedia.BuildConfig
import ru.netology.nmedia.R
import ru.netology.nmedia.viewmodel.PostViewModel

class AttachmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attachment, container, false)
    }

//    val attachmentUrl = "${BuildConfig.BASE_URL}/images/${post.attachment.url}"
//    val attachmentUrlNew = "${BuildConfig.BASE_URL}/media/${post.attachment.url}"
//    Glide.with(binding.attachment)
//        .load(attachmentUrl)
//        .load(attachmentUrlNew)
//        .circleCrop()
//        .timeout(10_000)
//        .into(binding.avatar)

}