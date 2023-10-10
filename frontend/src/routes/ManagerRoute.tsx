import { Navigate, Outlet, useNavigate } from "react-router-dom";
import useAuth from "../context/AuthContext";

export default () => {
  const { roles } = useAuth();

  if (!roles.includes("MANAGER")) {
    return <Navigate to="/" />;
  } else {
    return <Outlet />;
  }
};
