import com.example.digitoon.model.Film
import io.reactivex.Completable
import io.reactivex.Single

interface FilmDataSource {

    fun getFilms(): Single<List<Film>>

    fun insertFilms(films: List<Film>): Completable

}