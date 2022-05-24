import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.ryckandmorty.data.model.Episode
import com.example.ryckandmorty.data.remote.EpisodeService
import java.io.IOException

class EpisodePagingSource(
    private val service: EpisodeService
) : PagingSource<Int, Episode>() {

    private var episode: List<Episode> = listOf()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        return try {
            val result = service.getEpisodes(params.key ?: STARTING_PAGE_INDEX)

            result.body()?.episode?.let {
                episode = it
            }

            LoadResult.Page(
                data = episode,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        TODO("Not yet implemented")
    }
}