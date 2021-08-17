package com.ismailamassi.presentation.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    val fakeCategories = listOf(
        CategoryDto(1L, "Fast Food", "https://image.flaticon.com/icons/png/512/3447/3447891.png"),
        CategoryDto(5L, "Cake", "https://image.flaticon.com/icons/png/128/448/448003.png"),
        CategoryDto(2L, "Sweets", "https://image.flaticon.com/icons/png/128/3135/3135296.png"),
        CategoryDto(3L, "Meals", "https://image.flaticon.com/icons/png/128/3274/3274099.png"),
        CategoryDto(4L, "Fish", "https://image.flaticon.com/icons/png/128/858/858398.png"),
        CategoryDto(
            6L,
            "Chinese Food And Korean Asia",
            "https://image.flaticon.com/icons/png/512/4389/4389151.png"
        ),
        CategoryDto(7L, "Empty", "https://image.flaticon.com/icons/png/128/3082/3082031.png"),
        CategoryDto(
            8L,
            "Starchy food",
            "https://image.flaticon.com/icons/png/128/2912/2912292.png"
        ),
        CategoryDto(9L, "Vegetables", "https://image.flaticon.com/icons/png/128/782/782938.png"),
        CategoryDto(10L, "Legumes", "https://image.flaticon.com/icons/png/128/1744/1744944.png"),
        CategoryDto(11L, "Nuts", "https://image.flaticon.com/icons/png/128/877/877951.png"),
        CategoryDto(
            12L,
            "Fruit juice",
            "https://image.flaticon.com/icons/png/128/5406/5406679.png"
        ),
//        CategoryDto(13L, "Another Cat", "https://image.flaticon.com/icons/png/128/5406/5406679.png"),
        CategoryDto(
            14L,
            "Another Cat",
            "https://image.flaticon.com/icons/png/128/5396/5396716.png"
        ),
        CategoryDto(15L, "Error", "https://image.flaticon.com/icons/png/128/5392/5392032.png"),
    )
    val fakeRecipes = listOf(
        RecipeDto(
            1,
            "Hamburger",
            1,
            "https://image.flaticon.com/icons/png/512/3075/3075929.png",
            "",
            0,
            1
        ),
        RecipeDto(
            2,
            "Pizza",
            1,
            "https://image.flaticon.com/icons/png/512/3132/3132693.png",
            "",
            0,
            1
        ),
        RecipeDto(
            3,
            "Naqaneq",
            1,
            "https://image.flaticon.com/icons/png/512/2979/2979274.png",
            "",
            0,
            1
        ),

        RecipeDto(
            4,
            "Recipe 2_1",
            1,
            "https://image.flaticon.com/icons/png/512/3496/3496528.png",
            "",
            0,
            2
        ),
        RecipeDto(
            5,
            "Recipe 2_2",
            1,
            "https://image.flaticon.com/icons/png/512/2674/2674067.png",
            "",
            0,
            2
        ),
        RecipeDto(
            6,
            "Recipe 2_3",
            1,
            "https://image.flaticon.com/icons/png/512/1404/1404945.png",
            "",
            0,
            2
        ),

        RecipeDto(
            7,
            "Recipe 3_1",
            1,
            "https://image.flaticon.com/icons/png/512/706/706164.png",
            "",
            0,
            3
        ),
        RecipeDto(
            8,
            "Recipe 3_2",
            1,
            "https://image.flaticon.com/icons/png/512/1147/1147832.png",
            "",
            0,
            3
        ),
        RecipeDto(
            9,
            "Recipe 3_3",
            1,
            "https://image.flaticon.com/icons/png/512/3649/3649227.png",
            "",
            0,
            3
        ),

        RecipeDto(
            10,
            "Recipe 4_1",
            1,
            "https://image.flaticon.com/icons/png/512/3967/3967371.png",
            "",
            0,
            4
        ),
        RecipeDto(
            11,
            "Recipe 4_2",
            1,
            "https://image.flaticon.com/icons/png/512/561/561611.png",
            "",
            0,
            4
        ),
        RecipeDto(
            12,
            "Recipe 4_3",
            1,
            "https://image.flaticon.com/icons/png/512/2521/2521122.png",
            "",
            0,
            4
        ),

        RecipeDto(
            13,
            "Recipe 4_4",
            1,
            "https://image.flaticon.com/icons/png/512/851/851298.png",
            "",
            0,
            4
        ),
        RecipeDto(
            14,
            "Recipe 4_5",
            1,
            "https://image.flaticon.com/icons/png/512/1947/1947969.png",
            "",
            0,
            4
        ),
        RecipeDto(
            15,
            "Recipe 4_6",
            1,
            "https://image.flaticon.com/icons/png/512/2190/2190839.png",
            "",
            0,
            4
        ),

        RecipeDto(
            16,
            "Recipe 4_7",
            1,
            "https://image.flaticon.com/icons/png/512/2718/2718224.png",
            "",
            0,
            4
        ),
        RecipeDto(
            17,
            "Recipe 4_8",
            1,
            "https://image.flaticon.com/icons/png/512/3067/3067801.png",
            "",
            0,
            4
        ),
        RecipeDto(
            18,
            "Recipe 4_9",
            1,
            "https://image.flaticon.com/icons/png/512/3800/3800515.png",
            "",
            0,
            4
        ),

        RecipeDto(
            19,
            "Recipe 4_10",
            1,
            "https://image.flaticon.com/icons/png/512/3448/3448015.png",
            "",
            0,
            4
        ),
        RecipeDto(
            20,
            "Recipe 4_11",
            1,
            "https://image.flaticon.com/icons/png/512/2401/2401970.png",
            "",
            0,
            4
        ),
    )

    val fakeIngredients = listOf(
        IngredientDto(1, "Ingredient 1_1 Ingredient 1_1 Ingredient 1_1", "1 1/2", "Cup", fakeRecipes[0].id),
        IngredientDto(2, "Ingredient 1_2 Ingredient 1_2 Ingredient 1_2", "3", "oz", fakeRecipes[0].id),
        IngredientDto(3, "Ingredient 1_3 Ingredient 1_3 Ingredient 1_3", "50", "g", fakeRecipes[0].id),
        IngredientDto(4, "Ingredient 1_4 Ingredient 1_4 Ingredient 1_4", "5", "Cup", fakeRecipes[0].id),
        IngredientDto(5, "Ingredient 1_5 Ingredient 1_5 Ingredient 1_5", "6", "Piece", fakeRecipes[0].id),
        IngredientDto(5, "Ingredient 1_6 Ingredient 1_6 Ingredient 1_6", "3", "Piece", fakeRecipes[0].id),
        IngredientDto(5, "Ingredient 1_7 Ingredient 1_7 Ingredient 1_7", "1/2", "Cup", fakeRecipes[0].id),
        IngredientDto(5, "Ingredient 1_8 Ingredient 1_8 Ingredient 1_8", "200", "g", fakeRecipes[0].id),

        IngredientDto(6, "Ingredient 2_1", "1/2", "Cup", fakeRecipes[1].id),
        IngredientDto(7, "Ingredient 2_2", "1/2", "Cup", fakeRecipes[1].id),
        IngredientDto(8, "Ingredient 2_3", "1/2", "Cup", fakeRecipes[1].id),

        IngredientDto(9, "Ingredient 3_1", "30", "oz", fakeRecipes[2].id),
        IngredientDto(10, "Ingredient 3_2", "13", "oz", fakeRecipes[2].id),
        IngredientDto(11, "Ingredient 3_3", "13", "oz", fakeRecipes[2].id),
        IngredientDto(12, "Ingredient 3_4", "13", "oz", fakeRecipes[2].id),

        IngredientDto(13, "Ingredient 4_1", "300", "g", fakeRecipes[3].id),
        IngredientDto(14, "Ingredient 4_2", "300", "g", fakeRecipes[3].id),
        IngredientDto(15, "Ingredient 4_3", "300", "g", fakeRecipes[3].id),
        IngredientDto(16, "Ingredient 4_4", "300", "g", fakeRecipes[3].id),
        IngredientDto(17, "Ingredient 4_5", "300", "g", fakeRecipes[3].id),
        IngredientDto(18, "Ingredient 4_6", "300", "g", fakeRecipes[3].id),
    )

    val fakeSteps = listOf(
        StepDto(1, 1, "Step 1_1 Step 1_1 Step 1_1 Step 1_1 Step 1_1 Step 1_1 Step 1_1 Step 1_1 Step 1_1 Step 1_1", fakeRecipes[0].id),
        StepDto(2, 2, "Step 1_2 Step 1_2 Step 1_2 Step 1_2 Step 1_2 Step 1_2 Step 1_2 Step 1_2 Step 1_2 Step 1_2", fakeRecipes[0].id),
        StepDto(3, 3, "Step 1_3 Step 1_3 Step 1_3 Step 1_3 Step 1_3 Step 1_3 Step 1_3 Step 1_3 Step 1_3 Step 1_3", fakeRecipes[0].id),
        StepDto(4, 4, "Step 1_4 Step 1_4 Step 1_4 Step 1_4 Step 1_4 Step 1_4 Step 1_4 Step 1_4 Step 1_4 Step 1_4", fakeRecipes[0].id),

        StepDto(5, 1, "Step 2_1", fakeRecipes[1].id),
        StepDto(6, 2, "Step 2_2", fakeRecipes[1].id),
        StepDto(7, 3, "Step 2_3", fakeRecipes[1].id),
        StepDto(8, 4, "Step 2_4", fakeRecipes[1].id),
        StepDto(9, 5, "Step 2_5", fakeRecipes[1].id),


        StepDto(9, 5, "Step 3_1 Step 3_1 Step 3_1 Step 3_1 Step 3_1 Step 3_1", fakeRecipes[2].id),
        StepDto(9, 5, "Step 3_2 Step 3_2 Step 3_2 Step 3_2 Step 3_2 Step 3_2", fakeRecipes[2].id),
        StepDto(9, 5, "Step 3_3 Step 3_3 Step 3_3 Step 3_3 Step 3_3 Step 3_3", fakeRecipes[2].id),
        StepDto(9, 5, "Step 3_4 Step 3_4 Step 3_4 Step 3_4 Step 3_4 Step 3_4", fakeRecipes[2].id),
    )

    val fakeTips = listOf(
        TipDto(
            1,
            "جهزي لوجبة اليوم التالي لتوفري على نفسك الكثير من وقت الطبخ، فالتحضير هو سر النجاح"
        ),
        TipDto(2, "ارفعي درجة حرارة الفرن مع تقليل وقت الطهي، كلما صغر حجم الشيء المخبوز"),
        TipDto(
            3,
            "احفظي التوابل في مكان بارد جاف ومعتم، إذ إن الحرارة والرطوبة والضوء من شأنها أن تفقد البهارات نكهتها"
        ),
        TipDto(
            4,
            "ابشري القليل من قشر الفاكهة، مثل البرتقال أو التفاح على السلطة، لإضفاء نكهة مميزة لها"
        ),
        TipDto(
            5,
            "اطهي المرق بكميات كبيرة وخزنيه في أكياس بلاستيك بالفريزر، فهكذا يكون بإمكانك أن تستخدميه لعمل الحساء أو الصلصات المتنوعة بسهولة عند الحاجة"
        ),
        TipDto(
            6,
            "إذا كانت لديكِ عزومة، لا تجربي وصفات جديدة، اطهي الأصناف التي تبرعين في إعدادها بشكل جيد"
        ),
        TipDto(
            7,
            "بعد قلي البيض العيون وإخراجه، ضعي القليل من خل الفاكهة (التفاح أو العنب) في الإناء نفسه وسخنيه قليلًا، ثم أضيفيه على البيض لإضفاء نكهة جديدة له"
        ),
        TipDto(
            8,
            "بعد تحضير الثوم، امسحي يديكِ بشدة فى حوض المطبخ الاستانلس استيل قبل غسلهما، سوف تزول الرائحة من يديكِ تمامًا"
        ),
        TipDto(9, "ضعي الدواجن في محلول ملحي قبل طهيها، حتى تظهر نكهتها بقوة في أثناء الطهي"),
        TipDto(
            10,
            " اسلقي المكرونة لمدة أقل بدقيقة عما ينصح به على العبوة، ثم حضري الصلصة الخاصة بها"
        ),
    )


    var hostActivity: Activity? = null
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = _binding as VB

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()

    }

    fun doOnUI(func: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch { func() }
    }

    fun doOnIO(func: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch { func() }
    }

    fun showSnackBar(
        message: String,
        actionText: String = "",
        listener: View.OnClickListener? = null
    ) {
        Snackbar
            .make(requireView(), message, Snackbar.LENGTH_LONG)
            .apply {
                if (actionText.isNotEmpty())
                    setAction("Action", listener)
                show()
            }
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showDialog() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = if (context is MainActivity) context
        else context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        hostActivity = null
    }

    companion object {
        const val TAG = "BaseFragment"
    }
}