Приложения "Foodies"

Это приложение представляет собой приложения доставки еды с возможностью просмотра каталога товаров, добавления и удаления товаров из корзины, а также просмотра информации о каждом товаре.  
Дополнительно реализован личный кабинет с возможностью входа через Google аккаунт и сохранением истории заказов, и добавлением в избранное понравившихся блюд.

Функциональность:

Просмотр каталога товаров с использованием вкладок для навигации по категориям.
Отображение карточек товаров с изображением, названием, весом/объемом, ценой и другими характеристиками.
Добавление товаров в корзину из каталога и из карточки товара.
Редактирование количества товара в корзине.
Удаление товаров из корзины.
Отображение общего количества товаров на главном экране.
Отображение пустого состояния корзины и каталога, если данные отсутствуют.
Использование анимаций для более привлекательного пользовательского опыта.

Технологии: Kotlin, Jetpack Compose, Clean Architecture, Kotlin Coroutines и Flow, Coil, Room, Retrofit, Gson, Hilt, Lottie, Firebase.

Проект разделен на следующие модули:  
*  presentation: содержит код пользовательского интерфейса и логику отображения экранов.  
*  domain: содержит бизнес-логику и интерфейсы репозиториев.  
*  data: реализация репозиториев и взаимодействие с внешними источниками данных.  
*  common: общие утилиты и расширения. 
*  auth: модуль аутентификации через Firebase.

Как использовать:  
*  При запуске приложения отобразится экран Логина, на котором два варианта входа: Без регистрации/Войти через гугл аккаунт.  
*  Если выбрать без регистрации, то попадете на экран каталога. Возможности делать заказ и добавлять в избранное не будет.  
*  Если выбрать вход через гугл аккаунт, то появится возможность делать заказ, просматривать исторою заказов, а так же добавлять блюда в избранное.
*  после закрытия приложения без выхода из аккаунта, то при новой загрузке приложения вход произойдет автоматически.  
*  Используйте вкладки навигации, чтобы переключаться между категориями.   
*  Для просмотра подробной информации о товаре нажмите на его карточку.    
*  Чтобы добавить товар в корзину, нажмите кнопку "Добавить в корзину" на карточке товара.  
*  Для просмотра содержимого корзины нажмите на кнопку "Корзина".   
*  В корзине вы можете увеличивать или уменьшать количество товара, а также удалять товары из корзины.
*  После завершения покупок нажмите кнопку "Оформить заказ".


Приложение разработал Михаил Позялов.

Для Nti.team  
Трудности с которыми я столкнулся:  

Цвет фона у SplashScreen сделан не по макету, так как я не смог сделать разный цвет статус бара в зависимости от экрана. Цвет лого белый, а на анимации он черный, поэтому цвет фона я выбрал белый, как у всего приложения. 
