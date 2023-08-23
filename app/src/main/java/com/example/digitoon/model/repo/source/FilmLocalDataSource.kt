import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digitoon.model.Film
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FilmLocalDataSource : FilmDataSource {

    @Query("SELECT * FROM film")
    override fun getFilms(): Single<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertFilms(films: List<Film>): Completable

}