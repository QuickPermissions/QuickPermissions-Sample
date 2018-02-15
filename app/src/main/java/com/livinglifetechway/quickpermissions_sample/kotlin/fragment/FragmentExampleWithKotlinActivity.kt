package com.livinglifetechway.quickpermissions_sample.kotlin.fragment

import android.Manifest
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.livinglifetechway.k4kotlin.onClick
import com.livinglifetechway.k4kotlin.toastNow
import com.livinglifetechway.k4kotlin.transact
import com.livinglifetechway.quickpermissions.annotations.OnPermissionsDenied
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import com.livinglifetechway.quickpermissions.util.QuickPermissionsRequest

import com.livinglifetechway.quickpermissions_sample.R
import kotlinx.android.synthetic.main.activity_fragment_example_with_kotlin.*
import kotlinx.android.synthetic.main.fragment_fragment_child_fragment.view.*
import kotlinx.android.synthetic.main.fragment_fragment_example_with_kotlin.view.*

class FragmentExampleWithKotlinActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_example_with_kotlin)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_fragment_example_with_kotlin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_fragment_example_with_kotlin, container, false)
            rootView.button_location.onClick {
                methodWithPermission()
            }

            // add child fragment
            childFragmentManager.transact {
                add(R.id.container,ChildPlaceholderFragment())
            }

            return rootView
        }

        @WithPermissions(permissions = [Manifest.permission.ACCESS_FINE_LOCATION])
        private fun methodWithPermission() {
            toastNow("You have got the location permission from the fragment!")
        }

        @OnPermissionsDenied
        fun locationPermissionDenied(req: QuickPermissionsRequest) {
            toastNow("${req.deniedPermissions.size} permission(s) denied")
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    /**
     * A chilplaceholder fragment containing a simple view.
     */
    class ChildPlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_fragment_child_fragment, container, false)
            rootView.button_storage.onClick {
                methodWithPermission()
            }
            return rootView
        }

        @WithPermissions(permissions = [Manifest.permission.WRITE_EXTERNAL_STORAGE])
        private fun methodWithPermission() {
            toastNow("You have got the storage permission from the child fragment!")
        }

        @OnPermissionsDenied
        fun storagePermissionDenied(req: QuickPermissionsRequest) {
            toastNow("${req.deniedPermissions.size} permission(s) denied")
        }

    }

}
