import React from "react";
import ReactDOM from "react-dom/client";
import Login from "./pages/Login.tsx";
import SignUp from "./pages/SignUp.tsx";
import "./index.css";
import { AuthProvider } from "./context/AuthContext.tsx";
import Welcome from "./pages/Welcome.tsx";
import ProtectedRoute from "./routes/ProtectedRoute.tsx";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  createBrowserRouter,
} from "react-router-dom";
import Manager from "./pages/Manager.tsx";
import ManagerRoute from "./routes/ManagerRoute.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/sign-up" element={<SignUp />} />

          <Route element={<ProtectedRoute />}>
            <Route path="/" element={<Welcome />} />
            <Route element={<ManagerRoute />}>
              <Route path="/manager" element={<Manager />} />
            </Route>
          </Route>
        </Routes>
      </Router>
    </AuthProvider>
  </React.StrictMode>
);
