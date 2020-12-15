package com.example.trainingapp.queue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.Dialogs
import com.example.trainingapp.R
import com.example.trainingapp.core.Keys
import com.example.trainingapp.core.Prefs
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.repositories.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class QueueFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: QueueAdapter
    lateinit var exerciseList: ArrayList<Exercise>
    lateinit var saveButton: Button
    var trainingId = 0L

    @Inject
    lateinit var exerciseRepository: ExerciseRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_queue, container, false)
        recyclerView = view.findViewById(R.id.queueRecycler)
        saveButton = view.findViewById(R.id.saveQueue)

        TrainingApp.component.injectQueueFragment(this)
        trainingId = Prefs.getLong(Keys.TRAINING_ID)
        CoroutineScope(Dispatchers.IO).launch {
            getTraining()
            createRecycler()
        }



        saveButton.setOnClickListener { button ->

                    button.findNavController().navigate(R.id.mainFragment)
        }

        return view
    }


    suspend fun getTraining() {
        exerciseList = exerciseRepository.getTraining(trainingId)
        withContext(Dispatchers.Main) {
            adapter = QueueAdapter(exerciseList, (fun(exercise: Exercise) {
                CoroutineScope(Dispatchers.IO).launch {
                    exerciseRepository.deleteExercise(exercise)

                }
            }), (fun(exercise: Exercise) {
                Dialogs.CreateExercise(requireContext()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val exercise1 =    Exercise(
                            exercise.image,
                            exercise.name,
                            exercise.training_id,
                            adapter.adapterList.size + 1
                        )
                        val id = exerciseRepository.insert(exercise1)

                        exercise1.id = id
                        withContext(Dispatchers.Main) {
                            adapter.addItem(exercise1)
                        }
                    }
                }
            })) {
                var i = 0
                    adapter.adapterList.forEach{
                        it.queue = i
                        i++
                    }
                CoroutineScope(Dispatchers.IO).launch {

                    exerciseRepository.updateAll(adapter.adapterList)
                }
            }
        }
    }

    suspend fun createRecycler() {
        withContext(Dispatchers.Main) {
            val manager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = manager
            val dividerItemDecoration = DividerItemDecoration(requireContext(), manager.orientation)
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.adapter = adapter
            val callback = DragManageAdapter(
                adapter,
                requireContext(),
                ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
                ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
            )
            val helper = ItemTouchHelper(callback)
            helper.attachToRecyclerView(recyclerView)
        }
    }

}


