package ro.iss.trainingexercises

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RetrofitCall {

    fun getQuestions() : Observable<ArrayList<Question>> {
        return RetrofitClient.getQuizClient()
            .getQuestions()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

}