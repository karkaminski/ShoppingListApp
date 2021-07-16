package pl.karkaminski.shoppinglistapp.ui.mainviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import pl.karkaminski.shoppinglistapp.databinding.MainPagerFragmentBinding;
import pl.karkaminski.shoppinglistapp.ui.shoppinglists.ShoppingListsFragment;

public class MainViewPagerFragment extends Fragment {

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainPagerFragmentBinding binding = MainPagerFragmentBinding.inflate(inflater, container, false);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShoppingListsFragment(true));
        fragments.add(new ShoppingListsFragment(false));

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(
                requireActivity().getSupportFragmentManager(),
                getLifecycle(),
                fragments
        );

        binding.viewPager.setAdapter(adapter);

        return binding.getRoot();
    }
}