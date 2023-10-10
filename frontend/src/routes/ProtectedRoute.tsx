import { Outlet, Navigate } from "react-router-dom";
import useAuth from "../context/AuthContext";

export default () => {
  const { isAuth } = useAuth();

  if (!isAuth) {
    return <Navigate to="/login" />;
  } else {
    return <Outlet />;
  }
};
