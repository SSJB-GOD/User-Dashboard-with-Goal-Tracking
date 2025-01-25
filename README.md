# User Dashboard and Goals

## Features

- User Registration and Authentication
- API endpoint testing using Postman
-  User List: Display a list of users with name, email, and the number of goals they have. 
2. Search and Sort: Add a search bar to filter users by name or email and allow sorting by 
name. 
3. Add User: Include a form to add a new user (name, email). 
4. User Details: Clicking on a user shows their goals in a sidebar or modal, with each goal 
showing its title, deadline, and status (e.g., "In Progress," "Completed"). 
5. Goal Tracking Dashboard: Add a summary section displaying total goals, completed 
goals, and a percentage of completion for all users combined. 

---

## Backend Setup (Spring Boot)

### Prerequisites
- Java 17
- Spring Boot 3.x
- H2
- React




### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo.git
   cd your-repo/backend
   ```

   ```
2. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

---

## Frontend Setup (React with Vite)

### Prerequisites
- Node.js (v16+)

### Steps
1. Navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install axios react-router-dom 
   ```
3. Start the application:
   ```bash
   npm start
   ```

### Packages Used
- **Axios**: For making HTTP requests to backend APIs
- **React Router DOM**: For client-side routing

---

## Testing API Endpoints

### Tools Used
- Postman

### Steps
1. Import the Postman collection included in the `docs` folder.
2. Configure the environment with the backend URL (e.g., `http://localhost:8080`).


#### Example Postman Collection Screenshot:

---

## Project Execution

1. **Backend**:
   - User Authentication module runs on port `8080`.

2. **Frontend**:
   - Runs on port `3001` using React.

---

## Contributing
Feel free to fork the repository, create a branch, and submit a pull request. Contributions are welcome!

---

## License
This project is licensed under the MIT License.

