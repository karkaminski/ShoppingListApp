package pl.karkaminski.shoppinglistapp.data

var shoppingListDetails1 = arrayListOf<ShoppingListDetail>(
    ShoppingListDetail(1, "Potatoes", 10, true),
    ShoppingListDetail(2, "Tomatoes", 1, false),
    ShoppingListDetail(3, "Soda", 3, true),
    ShoppingListDetail(4, "Pumpkin", 1, true),
    ShoppingListDetail(5, "Dog food", 11, false),
    ShoppingListDetail(6, "Cat food", 2, true),
    ShoppingListDetail(7, "Fish food", 3, false),
    ShoppingListDetail(8, "Human food", 100, true),
    ShoppingListDetail(9, "Salad", 1, true),
    ShoppingListDetail(10, "Eggs", 10, false),
)

var shoppingListDetails2 = arrayListOf<ShoppingListDetail>(
    ShoppingListDetail(1, "Potatoes", 10, false),
    ShoppingListDetail(2, "Tomatoes", 1, false),
    ShoppingListDetail(3, "Soda", 3, false),
    ShoppingListDetail(4, "Pumpkin", 1, false),
    ShoppingListDetail(5, "Dog food", 11, false),
    ShoppingListDetail(6, "Cat food", 2, false),
    ShoppingListDetail(7, "Fish food", 3, false),
    ShoppingListDetail(8, "Human food", 100, false),
    ShoppingListDetail(9, "Salad", 1, false),
    ShoppingListDetail(10, "Eggs", 10, false),
)

var shoppingListDetails3 = arrayListOf<ShoppingListDetail>(
    ShoppingListDetail(1, "Potatoes", 10, true),
    ShoppingListDetail(2, "Tomatoes", 1, true),
    ShoppingListDetail(3, "Soda", 3, true),
    ShoppingListDetail(4, "Pumpkin", 1, true),
    ShoppingListDetail(5, "Dog food", 11, true),
    ShoppingListDetail(6, "Cat food", 2, true),
    ShoppingListDetail(7, "Fish food", 3, true),
    ShoppingListDetail(8, "Human food", 100, true),
    ShoppingListDetail(9, "Salad", 1, true),
    ShoppingListDetail(10, "Eggs", 10, true),
)

var shoppingLists = listOf(
    ShoppingList(1, "First - active", shoppingListDetails1, true),
    ShoppingList(2, "Second - inactive", shoppingListDetails2, false),
    ShoppingList(3, "Third - active", shoppingListDetails3, true),
    ShoppingList(4, "Fourth - active", shoppingListDetails1, true),
    ShoppingList(5, "Fiveth - active", shoppingListDetails2,true),
    ShoppingList(6, "Sixth - active", shoppingListDetails3,true),
    ShoppingList(7, "Seventh - inactive", shoppingListDetails1,false),
    ShoppingList(8, "Eight - inactive", shoppingListDetails2,false),
    ShoppingList(9, "Nineth - active", shoppingListDetails3,true),
    ShoppingList(10, "Tenth - inactive", shoppingListDetails1,false),
)

