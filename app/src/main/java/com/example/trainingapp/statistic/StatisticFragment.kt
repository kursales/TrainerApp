package com.example.trainingapp.statistic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import javax.inject.Inject


class StatisticFragment : Fragment() {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter: StatisticAdapter
    lateinit var statistic: AppStatistic

    @Inject
    lateinit var completeExerciseRepository: CompleteExerciseRepository




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.statisticRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        statistic = AppStatistic(completeExerciseRepository)


    }

    suspend fun bindCards(){
        val cardList = ArrayList<Card>()
        cardList.add(Card("любимое упражнение: ${statistic.getFavoriteName()}", statistic.max))
    }

}

