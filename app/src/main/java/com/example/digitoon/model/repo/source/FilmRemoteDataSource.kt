import com.example.digitoon.model.Film
import com.example.digitoon.tech.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class FilmRemoteDataSource(val apiService: ApiService) :FilmDataSource {

    override fun getFilms(): Single<List<Film>> = apiService.getFilms()

    override fun insertFilms(films: List<Film>): Completable {
        TODO("Not yet implemented")
    }

}