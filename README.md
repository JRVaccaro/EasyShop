# Easy Shop
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
## 🚀Description of the Project🚀
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

Welcome to EasyShop! Your go-to shopping cart API built with Java & Spring Boot! 🛒✨

This project lets logged-in users easily add, update, and manage products in their shopping cart. Whether you want to add that cool gadget, update quantities, peek at what’s in your cart, or just clear it out, this API has got you covered! 💪


## Interesting Code Snippet
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

@Test
@WithMockUser(username = "tater", roles = {"USER"})
void clearCart_ShouldReturn200() throws Exception {
// Create a fake User object to simulate userDao behavior
User user = new User();
user.setId(1);
user.setUsername("tater");

    // Mock userDao to return the fake user when "tater" is queried
    Mockito.when(userDao.getByUserName("tater")).thenReturn(user);

    // Perform DELETE request on cart and expect HTTP 200 OK response
    mockMvc.perform(delete("/cart"))
           .andExpect(status().isOk());

Demonstrates integration of Spring Security mock user with Mockito mocks 🔐

Shows how to test a secured DELETE endpoint using MockMvc 🎯

}


## User Stories
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
- As a user, I want to view the categories and their products, so that I can look through the catalog.
- As an admin, I want to add to the categories, so that I can expand the catalog with new product types for users to browse.
- As a admin I want to delete categories, so that I can keep it up to date.
- As a admin, I want to update category details, so that I can keep it up to date.
- As a user, I want the product search to return accurate results, so that I can find products that match my search.
- As a user, I want to update my account information, so that my account stays up to date.
- As an admin, I want to add new products to the catalog. So that I can keep it up to date.
- As a user, I want the product search to return accurate results, so that I can find products that match my search
- As a user, I want to view my profile information, so that I can see my account information.
- As a user, I want to have a profile made when I register, so that my account information will be saved
- As a user, I want the items I added to my cart to still be there when I log back in, so that I don't lose my selections.
- As a user, I want to be able to view items in my shopping cart, so that I can review what I plan to purchase before checking out.
- As an admin, I want to update an existing product. So that changes are correct without duplicating the product.
- As a user, I want to check out my items in my cart, so that I can place my order.
- As a user, I want to have my cart cleared after I check out, so that I don't have any leftover items.


## Setup
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
Getting EasyShop up and running is super easy! Just follow these steps and you’ll be ready to roll in no time. 🚀
1. Clone the repo
   git clone https://github.com/your-username/easyshop.git
   cd easyshop
2. Set up your database
   Create a new database called easyshop
Run the provided SQL scripts in /database folder to create tables and seed some data 🗄️
3. Open application.properties
Update your database connection info (URL, username, password) to match your setup 🔑
4. Test it out!
   Use Postman or your favorite API client
Hit http://localhost:8080/login to get your JWT token 🔐
Use the token to access cart endpoints and start adding those products! 🛍️



### Prerequisites
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'EasyshopApplication.main()'' to start the application.

## Technologies Used
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
- Java 17
- Intellij IDEA
- PostMan
- Spring Boot

## Demo
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
![EasyShopDemo - Made with Clipchamp.gif](Demo/EasyShopDemo%20-%20Made%20with%20Clipchamp.gif)

![website demo.gif](Demo/website%20demo.gif)
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
## 🚀 Future Versions
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹

While the core features of EasyShop are fully functional, there are still some exciting upgrades planned for the future:


🛒 Cart Check out: Allowing customers to be able to check out.

📦 Order History: Add a page where users can view past orders and receipts.

🌈 UI Polish: Improve layout, add responsive design, and spice up the visuals with better styling.

## 📚 Resources
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
- [Spring Boot MockBean Documentation](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/test/mock/mockito/MockBean.html)
- [Spring Boot Controller Testing Tutorial (YouTube)](https://www.youtube.com/watch?v=7lnevNCaTLQ)
- [Ray Maroun’s GitHub](https://github.com/RayMaroun)
- [ChatGPT (OpenAI)](https://chat.openai.com/)
## Team Members
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
- **Julianna Vaccaro** - Developed this project as part of my Java programming course 💻 a hands-on way to sharpen my skills using Spring Boot, REST APIs, and real-world project structure. It’s been a great opportunity to practice, debug, and build something functional 

## Thanks
🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
- Thank you to my instructor, Raymond, for providing skeleton code and your continuous support and guidance throughout the project.
- Thank you to my peers for always being willing to listen and support me when I needed it.
