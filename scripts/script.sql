INSERT into user_types (id, type) values (1, 'user')
INSERT into user_types (id, type) values (2, 'admin')


INSERT into measurements (id, measurement, proportion) values (1, 'grams', 1)
INSERT into measurements (id, measurement, proportion) values (2, 'millilitre', 1)
INSERT into measurements (id, measurement, proportion) values (3, 'teaspoon', 5)
INSERT into measurements (id, measurement, proportion) values (4, 'tablespoon', 15)

INSERT into users(id, username, email, password, user_type) values (1, 'admin', 'admin@gmail.com', '$2a$05$A61otk2T0PbqzemZJW2B7.7dwmMZHD6PFEjoqyh2aHJevFQQWMh/G', 2); /*sifra123*/
INSERT into users(id, username, email, password, user_type) values (2, 'mika', 'mika@gmail.com', '$2a$05$A61otk2T0PbqzemZJW2B7.7dwmMZHD6PFEjoqyh2aHJevFQQWMh/G', 1);


INSERT into recipes (id, title, description, number_of_portions, vegan, vegetarian, junk_food) values (1, 'Hummus', 'In the bowl of a food processor, combine the tahini and lemon juice and process for 1 minute, 
scrape the sides and bottom of the bowl then process for 30 seconds more. This extra time helps “whip” or “cream” the tahini, making the hummus smooth and creamy. 
Add the olive oil, minced garlic, cumin, and a 1/2 teaspoon of salt to the whipped tahini and lemon juice. Process for 30 seconds, scrape the sides and bottom of the bowl
 then process another 30 seconds or until well blended. Open, drain, and rinse the chickpeas. Add half of the chickpeas to the food processor and process for 1 minute.
 Scrape sides and bottom of the bowl, then add remaining chickpeas and process until thick and quite smooth; 1 to 2 minutes. Most likely the hummus will be too thick or still
 have tiny bits of chickpea. To fix this, with the food processor turned on, slowly add 2 to 3 tablespoons of water until you reach the perfect consistency. 
Taste for salt and adjust as needed. Serve hummus with a drizzle of olive oil and dash of paprika. Store homemade hummus in an airtight container and refrigerate up to one week.', 6, 'true', 'true', 'false');

INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (1, 'Chickpeas', 6, 61, 19, 'Grains');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (2, 'Lemon juice', 0.2, 7, 0.4, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (3, 'Tahini', 54, 21, 17, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (4,'Garlic', 0.5, 33, 6.4, 'Vegetable');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (5, 'Olive oil', 100, 0, 0, 'Oils');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (6, 'Water', 0, 0, 0, 'Other');


INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (1, 1, 4, 250);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (1, 2, 3, 60);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (1, 3, 4, 60);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (1, 4, 4, 5);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (1, 5, 3, 30);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (1, 6, 3, 45);


INSERT into recipes (id, title, description, number_of_portions, vegan, vegetarian, junk_food) values (2, 'Peanut butter cookies', 'Preheat oven to 350 degrees F. Line a baking sheet with parchment paper, then set aside.
Using a stand mixer (or hand mixer + large bowl), whip together granulated sugar, peanut butter, egg, and vanilla extract until thoroughly combined, about 8-10 minutes. Batter will be a little crumbly; this is okay.
Using a 1 tablespoon cookie scoop, scoop out dough and roll it between your hands. Place cookie ball on prepared baking sheet. Use a fork to gently press a criss-cross pattern in the dough (see above video for example.) Repeat this step until all dough is used, placing cookies about 2 inches apart on the baking sheet. 
Bake cookies for about 10 to 12 minutes or until cookies have dried and are no longer as shiny. Note that cookies will not expand or grow as much while baking; this is okay.
Let cookies rest on the baking sheet for 5-10 minutes, then transfer to a wire cooling rack to cool completely.', 24, 'false', 'true', 'false');


INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (7, 'Sugar', 0, 100, 0, 'Condiment');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (8, 'Peanut butter', 50, 20, 25, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (9, 'Egg', 11, 1.1, 13, 'Eggs');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (10, 'Vanilla extract', 0.1, 13, 0.1, 'Other');


INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 7, 1, 200);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 8, 1, 250);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 9, 1, 60);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 10, 2, 10);


INSERT into recipes (id, title, description, number_of_portions) values (3, 'Easy pancakes', 'Put 100g plain flour, 2 large eggs, 300ml milk, 1 tbsp sunflower or vegetable oil and a pinch of salt into a bowl or large jug, then whisk to a smooth batter. Set aside for 30 mins to rest if you have time, or start cooking straight away. 
Set a medium frying pan or crêpe pan over a medium heat and carefully wipe it with some oiled kitchen paper. When hot, cook your pancakes for 1 min on each side until golden, keeping them warm in a low oven as you go. Serve with lemon wedges and caster sugar, or your favourite filling. Once cold, you can layer the pancakes between baking parchment, then wrap in cling film and freeze for up to 2 months.', 6);


INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (11, 'Plain flour', 1, 76, 10, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (12, 'Milk', 1, 5, 3.4, 'Dairy');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (13, 'Sunflower oil', 100, 0, 0, 'Oils');


INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 11, 1, 100);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 9, 1, 60);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 12, 2, 300);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 13, 2, 15);


INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (14, 'Chicken breasts', 3.6, 0, 31, 'Meat');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (15, 'Salt', 0, 0, 0, 'Condiment');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (16, 'Black pepper', 3.26, 64.81, 10.95, 'Condiment');


INSERT into recipes (id, title, description, number_of_portions) values (4, 'Baked chicken breasts', 'Heat the oven. Preheat oven to 450°F. Season the chicken. Place the chicken breasts in a single layer in a large baking dish. Brush on both sides (turning once) evenly with the melted butter or olive oil. In a separate small bowl, whisk the salt, pepper, garlic powder and paprika until combined. Then sprinkle the mixture evenly over the chicken on both sides. Bake for 15-18 minutes, or until the chicken is cooked through and no longer pink. ', 4);


INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 14, 1, 700);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 15, 3, 1);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 16, 3, 0.5);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 5, 4, 1);