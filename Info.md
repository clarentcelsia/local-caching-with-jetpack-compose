FEATURES:
-
1. Data Package(==repository)
A: Menangani repository room database

2. Domain Package(== viewmodel)
A: Sebagai penghubung UI dengan Data

3. Presentation(==view)
A: UI Layer (@Composable)

SINGLE SOURCE OF TRUTH (Principle)
-
Source of the data comes from one source,
In caching, data which is gotten from the server will be saved in local database cache
which will also improve the speed of data transfer.
In another case, SSOT prevents the error occurred while accessing data from server
https://youtu.be/Ex9IT1bq0PQ

UI JetpackCompose (See UI JetpackCompose Documentation )
1. What is ScaffoldState?