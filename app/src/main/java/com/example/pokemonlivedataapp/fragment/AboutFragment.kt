import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.activities.DataListener
import com.example.pokemonlivedataapp.adapter.FormsRecyclerAdapter
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import com.example.pokemonlivedataapp.model.details.Type

class AboutFragment : Fragment(), DataListener {
    private lateinit var formsAdapter: FormsRecyclerAdapter
    private var formsList: List<Type> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formsRecycler: RecyclerView = view.findViewById(R.id.rvForms)
        formsAdapter = FormsRecyclerAdapter(formsList)
        formsRecycler.adapter = formsAdapter
    }

    override fun sendDataToFragment(data: PokemonDetails) {
        formsList = data.types
        formsAdapter.updateData(formsList)
    }
}
