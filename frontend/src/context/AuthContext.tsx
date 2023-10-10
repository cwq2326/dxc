import React from "react";
import { authReducer, initialAuthState } from "../reducer/AuthReducer";

type updateAuthPayload = {
  name: string;
  username: string;
  token: string;
  roles: string[];
  isAuth: boolean;
};

const AuthContext = React.createContext({
  username: "zzz",
  name: "",
  token: "",
  roles: [],
  isAuth: false,
  updateAuth: (auth: updateAuthPayload) => {},
  removeAuth: () => {},
});

export const AuthProvider = ({ children }) => {
  const [state, dispatch] = React.useReducer(authReducer, initialAuthState);

  const updateAuth = (auth: updateAuthPayload) => {
    dispatch({
      type: "LOGIN",
      payload: auth,
    });
  };

  const removeAuth = () => {
    dispatch({
      type: "LOGOUT",
      payload: null,
    });
  };

  const value = {
    username: state.username,
    name: state.name,
    token: state.token,
    roles: state.roles,
    isAuth: state.isAuth,
    updateAuth,
    removeAuth,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

const useAuth = () => {
  const context = React.useContext(AuthContext);

  if (context === undefined) {
    throw new Error("useAuth must be used within AuthContext");
  }

  return context;
};

export default useAuth;
