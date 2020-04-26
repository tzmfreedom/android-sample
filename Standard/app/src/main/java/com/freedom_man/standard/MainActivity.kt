package com.freedom_man.standard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tweet_row.view.*

class MainActivity : AppCompatActivity() {
    private var pager: ViewPager? = null
    private var adapter: FragmentPagerAdapter? = null
    private val titles: List<String> by lazy {
        listOf(
            getString(R.string.home),
            getString(R.string.search),
            getString(R.string.notification),
            getString(R.string.message)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        try {
//            Thread.sleep(2000)
//        } catch (e: InterruptedException) {
//
//        }
//        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.home)
        pager = findViewById<ViewPager>(R.id.pager).apply {
            this.adapter = PagerAdapter(
                supportFragmentManager, listOf(
                    TweetFragment(),
                    SearchFragment(),
                    MapsFragment(),
                    NameFragment("4")
                )
            )
        }
        pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                title = this@MainActivity.titles[position]
            }
        })
        tabLayout.setupWithViewPager(pager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_home_black_24dp)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_search_black_24dp)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_notifications_black_24dp)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_mail_outline_black_24dp)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        setSupportActionBar(toolbar)
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState()
    }

    class NameFragment(private val name: String) : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.tweet_row, container, false)
            view.body.text = name
            return view
        }
    }

    class PagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}
