import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    HashMap<String, ArrayList<String>> menuByType = new HashMap<>();
    Random random = new Random();

    public void saveAllDishes(String dishType, String dishName) {
        if (checkType(dishType) && checkName(dishName)) {
            System.out.println("Блюдо «" + dishName + "» уже сохранено в типе блюда «" + dishType + "».");
            System.out.println();
        } else {
            if (checkType(dishType)) {
                ArrayList<String> dishesNames = menuByType.get(dishType);
                dishesNames.add(dishName);
            } else {
                ArrayList<String> dishesNames = new ArrayList<>();
                dishesNames.add(dishName);
                menuByType.put(dishType, dishesNames);
            }
            System.out.println("Блюдо «" + dishName + "» сохранено в категорию «" + dishType + "».");
            System.out.println();
        }
    }

    boolean checkType(String type) {
        return menuByType.containsKey(type);
    }

    boolean checkName(String name) {
        for (ArrayList<String> dish : menuByType.values()) {
            if (dish.contains(name)) {
                return true;
            }
        }
        return false;
    }

    public void generateDishCombo(int numberOfCombos, ArrayList<String> selectedTypes) {
        System.out.println("Вот возможные комбинации:");

        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<ArrayList<String>> allCombos = new ArrayList<>();
            ArrayList<String> oneCombo = new ArrayList<>();

            for (String type : selectedTypes) {
                int indexOfDish = random.nextInt(menuByType.get(type).size());
                String dishName = menuByType.get(type).get(indexOfDish);
                oneCombo.add(dishName);
            }

            allCombos.add(oneCombo);

            for (ArrayList<String> combo : allCombos) {
                System.out.println("Комбо " + (i + 1) + ":");
                System.out.println(combo);
            }
        }
        System.out.println();
    }
}