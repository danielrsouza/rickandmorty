import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.ryckandmorty.data.model.Characters
import com.example.ryckandmorty.data.remote.CharactersService
import java.io.IOException

class CharactersPagingSource(
    private val service: CharactersService
) : PagingSource<Int, Characters>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        return try {
            val result = service.getCharacters(params.key ?: STARTING_PAGE_INDEX)

            LoadResult.Page(
                data = result.body()!!.characters,
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

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        TODO("Not yet implemented")
    }
}