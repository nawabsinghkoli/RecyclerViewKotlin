package com.example.recyclerviewkotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView : RecyclerView
    lateinit var newsArrayList : ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        myRecyclerView = findViewById(R.id.recyclerView)

        val newsImageArray = arrayOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6
        )

        val newsHeadingArray = arrayOf(
            "Bhimrao Ramji Ambedkar (Bhīmrāo Rāmjī Āmbēḍkar; 14 April 1891 – 6 December 1956) also called Babasaheb was an Indian jurist, economist, social reformer and political leader who headed the committee drafting the Constitution of India from the Constituent Assembly debates, served as Law and Justice minister in the first cabinet of Jawaharlal Nehru, and inspired the Dalit Buddhist movement after renouncing Hinduism.",
            "Ratan Naval Tata[a] (28 December 1937 – 9 October 2024) was an Indian industrialist and philanthropist. He served as the chairman of Tata Group and Tata Sons from 1991 to 2012 and he held the position of interim chairman from October 2016 to February 2017.[3][4] In 2000, he received the Padma Bhushan, the third highest civilian honour in India, followed by the Padma Vibhushan, the country's second highest civilian honour, in 2008.",
            "Avul Pakir Jainulabdeen Abdul Kalam BR (/ˈəbdʊl kəˈlɑːm/ ⓘ; 15 October 1931 – 27 July 2015) was an Indian aerospace scientist and statesman who served as the 11th president of India from 2002 to 2007. Born and raised in a Muslim family in Rameswaram, Tamil Nadu, he studied physics and aerospace engineering. He spent the next four decades as a scientist and science administrator, mainly at the Defence Research and Development Organisation (DRDO) and Indian Space Research Organisation (ISRO) and was intimately involved in India's civilian space programme and military missile development efforts.[2] He thus came to be known as the Missile Man of India for his work on the development of ballistic missile and launch vehicle technology.[3][4][5] He also played a pivotal organisational, technical, and political role in India's Pokhran-II nuclear tests in 1998, the first since the original nuclear test by India in 1974.",
            "Kalpana Chawla (17 March 1962 – 1 February 2003)[2] was an Indian-born American astronaut and aerospace engineer who was the first woman of Indian origin to fly to space.[3][4] She first flew on Space Shuttle Columbia in 1997 as a mission specialist and primary robotic arm operator aboard STS-87.[5] Chawla's second flight was in 2003 on STS-107, the final flight of Columbia. She was one of the seven crew members who died in the Space Shuttle Columbia disaster when the spacecraft disintegrated during its re-entry into the Earth's atmosphere on 1 February 2003.",
            "Mangte Chungneijang \"Mary\" Kom[2] (born 24 November 1982)[1] is an Indian Olympic boxer, politician, and former Member of Rajya Sabha.[3][4][5] She is the only woman to win the World Amateur Boxing Championship six times, the only female boxer to have won a medal in each one of the first seven World Championships, and the only boxer (male or female) to win eight World Championship medals.[6][7][8][9] Nicknamed Magnificent Mary, she was the only Indian female boxer to have qualified for the 2012 Summer Olympics, where she competed in the flyweight (51 kg) category and won a bronze medal.[10] She had also been ranked as the world's No. 1 female light-flyweight by the International Boxing Association (amateur) (AIBA).[11][12] She became the first Indian female boxer to win a gold medal in the Asian Games in 2014 at Incheon, South Korea and is the first Indian female boxer to win gold at the 2018 Commonwealth Games.[13] She is also the only boxer to become Asian Amateur Boxing Champion for a record six times.[14][15] Mary Kom won the 51kg gold in President's Cup in Indonesia.",
            "Amitabh Bachchan (pronounced [əmɪˈt̪ɑːbʱ ˈbətːʃən] ⓘ; born Amitabh Srivastava;[1] 11 October 1942[7]) is an Indian actor who works in Hindi cinema. He is often considered one of the greatest, most accomplished and commercially successful actors in the history of Indian cinema.[8] With a cinematic career spanning over five decades, he has played pivotal roles in over 200 films. Bachchan is often hailed as the Shahenshah of Bollywood, Sadi Ke Mahanayak (translated as \"Greatest actor of the century\" in Hindi), Star of the Millennium, or simply Big B.[9] His dominance in the Indian film industry during the 1970s–80s led the French director François Truffaut to describe it as a \"one-man industry\".[10] He is a recipient of several accolades including six National Film Awards and sixteen Filmfare Awards."
        )

        val newsContent = arrayOf(
            getString(R.string.news_content), getString(R.string.news_content),
            getString(R.string.news_content), getString(R.string.news_content),
            getString(R.string.news_content), getString(R.string.news_content)
        )

        // to set hav bhav of items inside recyclerview, vertcially scrolling, horizontally, uniform grid
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        newsArrayList = arrayListOf<News>()

        for( index in newsImageArray.indices){
            val news = News(newsHeadingArray[index], newsImageArray[index], newsContent[index])
            newsArrayList.add(news)
        }

        var myAdapter = MyAdapter(newsArrayList, this)
        myRecyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClicking(position: Int) {
                // on clicking each item , what action do you want to perform
                val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                intent.putExtra("heading", newsArrayList[position].newsHeading)
                intent.putExtra("imageId", newsArrayList[position].newsImage)
                intent.putExtra("newscontent", newsArrayList[position].newsContent)
                startActivity(intent)
            }
        })
    }
}