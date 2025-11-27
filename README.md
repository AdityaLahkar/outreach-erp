# Outreach Department ERP

## Prerequisites
*   Java 17+
*   Node.js & npm
*   MySQL Server

## Database Setup
1.  Ensure MySQL is running.
2.  Create the database and tables using the provided SQL script.
3.  Update `backend/src/main/resources/application.properties` with your MySQL credentials if they differ from the defaults.

## Running the Backend
1.  Navigate to the `backend` directory:
    ```bash
    cd backend
    ```
2.  Run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

## Running the Frontend
1.  Navigate to the `frontend` directory:
    ```bash
    cd frontend
    ```
2.  Install dependencies (first time only):
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm run dev
    ```
    The application will be accessible at `http://localhost:5173`.

## Google OAuth Setup
1.  Go to [Google Cloud Console](https://console.cloud.google.com/).
2.  Create a new project.
3.  Configure **OAuth Consent Screen** (External).
4.  Go to **Credentials** > **Create Credentials** > **OAuth client ID**.
5.  Set **Application type** to "Web application".
6.  Add `http://localhost:5173` to **Authorized JavaScript origins**.
7.  Copy the **Client ID**.
8.  Paste it into `frontend/src/App.jsx` replacing `YOUR_GOOGLE_CLIENT_ID_HERE`.

## Usage
*   **Login:** Click "Sign in with Google". Ensure the email you use matches one in the `Employees` table (e.g., `outreach.admin@gmail.com`).
*   **Dashboard:** View active placement offers.
*   **Details:** Click an offer to view eligible students and make selections.
