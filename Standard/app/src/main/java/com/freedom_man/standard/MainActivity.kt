package com.freedom_man.standard

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.freedom_man.standard.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val pager: ViewPager by lazy {
        findViewById<ViewPager>(R.id.pager)
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
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
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel.title.observe(this, Observer {
            Log.d("MAIN_ACTIVITY", it)
            binding.title = it
        })
        viewModel.title.postValue(titles[0])

        title = getString(R.string.home)
        pager.apply {
            this.adapter = PagerAdapter(
                supportFragmentManager, listOf(
                    TweetFragment.newInstance(),
                    SearchFragment.newInstance(),
                    MapsFragment.newInstance(),
                    OtherFragment.newInstance()
                )
            )
        }
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                viewModel.title.postValue(this@MainActivity.titles[position])
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

    class PagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}
