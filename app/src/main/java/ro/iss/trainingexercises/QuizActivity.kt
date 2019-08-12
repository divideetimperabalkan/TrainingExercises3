package ro.iss.trainingexercises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var quizAdapter: QuizAdapter
    private lateinit var observableGetQuestions: Observable<ArrayList<Question>>
    private lateinit var apiCallDisposableGetQuestions : Disposable
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        createLoadingDialog()
        configurationToolbar()
        getQuuestionList()

    }

    private fun createLoadingDialog() {
        loadingDialog = AlertDialog.Builder(this).
            setMessage( "Loading Questions")
            .create()
    }

    private fun configurationToolbar() {
        quiz_toolbar.title = "Submit Answers"
        setSupportActionBar(quiz_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun getQuuestionList() {
//        val questionsList = ArrayList<Question>()
//
//        for(i in 0 until 10) {
//            val answers = arrayListOf("Ans1","Ans2","Ans3", "Ans4")
//            questionsList.add(Question(1,"First Question?", answers))
//        }
//        return questionsList

        showLoadingDialog()
    observableGetQuestions = RetrofitCall.getQuestions()
    apiCallDisposableGetQuestions = observableGetQuestions.subscribe(
        {response ->
            setQuestionList(response)
        },
        {},
        {   dismissLoadingDialog()
            apiCallDisposableGetQuestions.dispose() }
    )
    }

    private fun setQuestionList(questions: ArrayList<Question>) {
        views_list.visibility = View.VISIBLE
        error_text_message.visibility = View.GONE
        quizAdapter = QuizAdapter(questions, this)
        views_list.adapter = quizAdapter
        views_list.layoutManager = LinearLayoutManager(this)
    }

    private fun getErrorMessasge() {
        views_list.visibility = View.GONE
        error_text_message.visibility = View.VISIBLE
    }

    private fun showLoadingDialog() {
        loadingDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) { android.R.id.home -> onBackPressed() }
        return super.onOptionsItemSelected(item)
    }


    private fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }
}
