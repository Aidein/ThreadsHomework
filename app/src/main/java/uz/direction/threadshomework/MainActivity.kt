package uz.direction.threadshomework

import android.os.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import uz.direction.threadshomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val compositeDisposable :CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShuffle.setOnClickListener{
            val progressBars = arrayListOf<LinearProgressIndicator>(
                binding.progress1,
                binding.progress2,
                binding.progress3,
                binding.progress4)

            var disposable : Disposable? = null

            for (i in 0 until progressBars.size){
                disposable = Service().getObserver().subscribe { progress ->
                    progressBars[i].progress = progress
                }
                if (disposable != null) {
                    compositeDisposable.add(disposable)
                }
            }
        }
    }
}
