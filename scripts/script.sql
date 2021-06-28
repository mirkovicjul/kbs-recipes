
TRUNCATE users cascade;
TRUNCATE history cascade;
TRUNCATE ingredient_storage cascade;
TRUNCATE ingredients cascade;
TRUNCATE measurement_converter cascade;
TRUNCATE recipe_ingredients cascade;
TRUNCATE recipe_storage cascade;
TRUNCATE recipes cascade;
TRUNCATE user_types cascade;
TRUNCATE measurements cascade;


INSERT into user_types (id, type) values (1, 'user');
INSERT into user_types (id, type) values (2, 'admin');


INSERT into measurements (id, measurement, proportion) values (1, 'grams', 1);
INSERT into measurements (id, measurement, proportion) values (2, 'millilitre', 1);
INSERT into measurements (id, measurement, proportion) values (3, 'teaspoon', 5);
INSERT into measurements (id, measurement, proportion) values (4, 'tablespoon', 15);


INSERT into users(id, username, email, password, user_type) values (1, 'admin', 'admin@gmail.com', '$2a$05$A61otk2T0PbqzemZJW2B7.7dwmMZHD6PFEjoqyh2aHJevFQQWMh/G', 2); /*sifra123*/
INSERT into users(id, username, email, password, user_type) values (2, 'mike', 'mike@brooklynvegan.com', '$2a$05$A61otk2T0PbqzemZJW2B7.7dwmMZHD6PFEjoqyh2aHJevFQQWMh/G', 1);
INSERT into users(id, username, email, password, user_type) values (3, 'junk', 'junkfood@gmail.com', '$2a$05$A61otk2T0PbqzemZJW2B7.7dwmMZHD6PFEjoqyh2aHJevFQQWMh/G', 1);
INSERT into users(id, username, email, password, user_type) values (4, 'new', 'newuser@gmail.com', '$2a$05$A61otk2T0PbqzemZJW2B7.7dwmMZHD6PFEjoqyh2aHJevFQQWMh/G', 1);


INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (1, 'Chickpeas', 6, 61, 19, 'Grains');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (2, 'Lemon juice', 0.2, 7, 0.4, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (3, 'Tahini', 54, 21, 17, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (4,'Garlic', 0.5, 33, 6.4, 'Vegetable');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (5, 'Olive oil', 100, 0, 0, 'Oils');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (6, 'Water', 0, 0, 0, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (7, 'Sugar', 0, 100, 0, 'Condiment');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (8, 'Peanut butter', 50, 20, 25, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (9, 'Egg', 11, 1.1, 13, 'Eggs');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (10, 'Vanilla extract', 0.1, 13, 0.1, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (11, 'Plain flour', 1, 76, 10, 'Other');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (12, 'Milk', 1, 5, 3.4, 'Dairy');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (13, 'Sunflower oil', 100, 0, 0, 'Oils');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (14, 'Chicken breasts', 3.6, 0, 31, 'Meat');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (15, 'Salt', 0, 0, 0, 'Condiment');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (16, 'Black pepper', 3.26, 64.81, 10.95, 'Condiment');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (17, 'Pineapple', 0.12, 12.63, 0.54, 'Fruit');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (18, 'Banana', 0.33, 22.84, 1.09, 'Fruit');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (19, 'Peach', 0.25, 9.54, 0.91, 'Fruit');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (20, 'Broccoli', 0.37, 6.64, 2.82, 'Vegetable');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (21, 'Cheddar', 33.31, 3.09, 22.87, 'Dairy');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (22, 'Edam', 27.80, 1.43, 24.99, 'Dairy');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (23, 'Apple', 0.17, 13.81, 0.26, 'Fruit');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (24, 'Potato', 0.09, 17.49, 2.05, 'Vegetable');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (25, 'Beef', 15, 0, 26, 'Meat');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (26, 'Pork', 14, 0, 27, 'Meat');
INSERT into ingredients (id, ingredient, fats, carbs, protein, type) values (27, 'Salami', 26, 2.4, 22, 'Meat');

INSERT into recipes (id, title, description, number_of_portions, vegan, vegetarian, junk_food) values (1, 'Hummus', 'In the bowl of a food processor, combine the tahini and lemon juice and process for 1 minute, 
scrape the sides and bottom of the bowl then process for 30 seconds more. This extra time helps “whip” or “cream” the tahini, making the hummus smooth and creamy. 
Add the olive oil, minced garlic, cumin, and a 1/2 teaspoon of salt to the whipped tahini and lemon juice. Process for 30 seconds, scrape the sides and bottom of the bowl
 then process another 30 seconds or until well blended. Open, drain, and rinse the chickpeas. Add half of the chickpeas to the food processor and process for 1 minute.
 Scrape sides and bottom of the bowl, then add remaining chickpeas and process until thick and quite smooth; 1 to 2 minutes. Most likely the hummus will be too thick or still
 have tiny bits of chickpea. To fix this, with the food processor turned on, slowly add 2 to 3 tablespoons of water until you reach the perfect consistency. 
Taste for salt and adjust as needed. Serve hummus with a drizzle of olive oil and dash of paprika. Store homemade hummus in an airtight container and refrigerate up to one week.', 6, 'true', 'true', 'false');
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
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 7, 1, 200);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 8, 1, 250);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 9, 1, 60);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (2, 10, 2, 10);


INSERT into recipes (id, title, description, number_of_portions) values (3, 'Easy pancakes', 'Put 100g plain flour, 2 large eggs, 300ml milk, 1 tbsp sunflower or vegetable oil and a pinch of salt into a bowl or large jug, then whisk to a smooth batter. Set aside for 30 mins to rest if you have time, or start cooking straight away. 
Set a medium frying pan or crêpe pan over a medium heat and carefully wipe it with some oiled kitchen paper. When hot, cook your pancakes for 1 min on each side until golden, keeping them warm in a low oven as you go. Serve with lemon wedges and caster sugar, or your favourite filling. Once cold, you can layer the pancakes between baking parchment, then wrap in cling film and freeze for up to 2 months.', 6);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 11, 1, 100);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 9, 1, 60);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 12, 2, 300);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (3, 13, 2, 15);

INSERT into recipes (id, title, description, number_of_portions) values (4, 'Baked chicken breasts', 'Heat the oven. Preheat oven to 450°F. Season the chicken. Place the chicken breasts in a single layer in a large baking dish. Brush on both sides (turning once) evenly with the melted butter or olive oil. In a separate small bowl, whisk the salt, pepper, garlic powder and paprika until combined. Then sprinkle the mixture evenly over the chicken on both sides. Bake for 15-18 minutes, or until the chicken is cooked through and no longer pink. ', 4);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 14, 1, 700);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 15, 3, 1);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 16, 3, 0.5);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (4, 5, 4, 1);


INSERT into recipes (id, title, description, number_of_portions, vegan, vegetarian, junk_food) values (5, 'French fries', 'Heat oil and slice the potatoes. Fry them in about 6 batches for 5-6 minutes until golden brown. 
Don’t overcrowd them by placing too many in at a time, they won’t be as crispy. Place them bake on paper towels and sprinkle immediately with salt. ', 3, 'true', 'true', 'true');

INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (5, 13, 2, 500);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (5, 24, 1, 1000);



INSERT into recipes (id, title, description, number_of_portions, vegan, vegetarian, junk_food) values (6, 'Salami pizza', 'Place Pizza Dough on a large sheet of parchment paper. 
Cover with a damp towel; let rise in a warm place (85°), free from drafts, 45 minutes. While dough rises, heat a large nonstick skillet over medium-high heat. Add oil; swirl to coat.
 Add mushrooms; cook 6 minutes or until tender, stirring frequently. Roll dough into a 12-inch circle on parchment paper. Crimp edges of dough with fingers to form a rim. 
 Slide dough and parchment paper onto a large baking sheet. Place on bottom rack in oven. Bake at 500° for 5 minutes. Spoon Pizza Sauce onto crust, spreading to rim. Top with salami, cheese, and mushrooms.
 Bake an additional 10 minutes or until crust is golden and cheese melts. Remove from oven; let stand 5 minutes. Cut into wedges.', 2, 'false', 'false', 'true');
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (6, 11, 1, 500);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (6, 13, 2, 100);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (6, 21, 1, 150);
INSERT into recipe_ingredients (recipe_id, ingredient_id, measurement_id, quantity) values (6, 27, 1, 200);

-- todo insert burger recipe
-- todo insert potato chips recipe
-- todo insert vegan recipe
-- todo insert vegetarian recipe

-- Vegan user 2
insert into history (id, user_id, recipe_id, servings, date) values (1, 2, 3, 12, '2021-05-01 04:05:06');
insert into history (id, user_id, recipe_id, servings, date) values (2, 2, 4, 2, '2021-05-03 04:05:06');
insert into history (id, user_id, recipe_id, servings, date) values (3, 2, 3, 2, '2021-05-04 04:05:06');
insert into history (id, user_id, recipe_id, servings, date) values (4, 2, 5, 10, '2021-06-06 04:05:06');
insert into history (id, user_id, recipe_id, servings, date) values (5, 2, 1, 5, '2021-06-07 04:05:06');
insert into history (id, user_id, recipe_id, servings, date) values (6, 2, 1, 5, '2021-06-08 04:05:06');
insert into history (id, user_id, recipe_id, servings, date) values (7, 2, 1, 5, '2021-06-08 04:05:06');

-- todo insert vegan recipe

insert into ingredient_storage (id, user_id, ingredient_id, quantity, measurement_id, best_before) values (1, 2, 11, 1000, 1, '2022-05-01 04:05:06');
insert into ingredient_storage (id, user_id, ingredient_id, quantity, measurement_id, best_before) values (2, 2, 13, 1000, 2, '2022-05-01 04:05:06');
insert into ingredient_storage (id, user_id, ingredient_id, quantity, measurement_id, best_before) values (3, 2, 14, 500, 1, '2021-06-30 04:05:06');

-- Junkfood user 3
insert into history (id, user_id, recipe_id, servings, date) values (8, 3, 6, 5, now() - interval '2 days');
insert into history (id, user_id, recipe_id, servings, date) values (9, 3, 6, 5, now() - interval '5 days');
insert into history (id, user_id, recipe_id, servings, date) values (10, 3, 6, 5, now() - interval '7 days');
insert into history (id, user_id, recipe_id, servings, date) values (11, 3, 6, 5, now() - interval '8 days');
insert into history (id, user_id, recipe_id, servings, date) values (12, 3, 6, 5, now() - interval '9 days');

insert into user_likes (user_id, ingredient_id) values (3, 27);
insert into user_likes (user_id, ingredient_id) values (3, 21);

-- todo insert burger a copule of times

-- New user 4
insert into user_likes (user_id, ingredient_id) values (4, 12);
insert into user_likes (user_id, ingredient_id) values (4, 24);

insert into user_dislikes (user_id, ingredient_id) values (4, 4);


