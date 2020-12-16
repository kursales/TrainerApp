package com.example.trainingapp.statistic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import com.example.trainingapp.db.repositories.NameTrainingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class StatisticFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: StatisticAdapter
    lateinit var statistic: AppStatistic
    lateinit var cardList: ArrayList<Card>

    @Inject
    lateinit var completeExerciseRepository: CompleteExerciseRepository

    @Inject
    lateinit var nametrainingRepository: NameTrainingRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TrainingApp.component.injectStatisticFragment(this)
        recyclerView = view.findViewById(R.id.statisticRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        statistic = AppStatistic(completeExerciseRepository, nametrainingRepository)
        CoroutineScope(Dispatchers.IO).launch {
            bindCards()
            adapter = StatisticAdapter(cardList)
            withContext(Dispatchers.Main) {
                recyclerView.adapter = adapter
            }
        }


    }

    suspend fun bindCards() {
        cardList = ArrayList<Card>()
        statistic.getExecuteExercises()
        val max = statistic.getFavoriteExercise()
        cardList.add(Card("любимое упражнение: ${statistic.getFavoriteName()}", max))
        cardList.add(Card("Всего выполнено упражнений", statistic.executeExercises))
        cardList.add(Card("Создано тренировок", statistic.getTrainingCount()))
        val training = statistic.getMaxCount()
        cardList.add(Card("Больше всего повторений:\n${training.first}", training.second))
        cardList.add(Card("Всего повторений за все время", statistic.getAllCount()))
        cardList.add(Card("Среднее количество повторений", statistic.getAverageCount()))
    }

}

