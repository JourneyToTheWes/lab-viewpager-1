package edu.uw.viewpager

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import kotlinx.android.synthetic.main.notification_template_lines_media.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SearchFragment : Fragment() {
    private var callback: OnSearchListener? = null

    internal interface OnSearchListener {
        fun onSearchSubmitted(searchTerm: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)

        val searchbtn = rootView.findViewById<Button>(R.id.btn_search)
        searchbtn.setOnClickListener {
            val text = rootView.findViewById<View>(R.id.txt_search)
            val searchTxt = text.text.toString()
            callback!!.onSearchSubmitted(searchTxt)
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            callback = context as OnSearchListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " does not implement OnSearchListener")
        }
    }

    companion object {

        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
