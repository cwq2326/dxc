export const initialAuthState = {
  username: "",
  name: "",
  token: "",
  roles: [],
  isAuth: false,
};

export const authReducer = (
  state: any,
  action: { type: any; payload: any }
) => {
  const { type, payload } = action;

  switch (type) {
    case "LOGIN": {
      return {
        username: payload.username,
        name: payload.name,
        token: payload.token,
        roles: payload.roles,
        isAuth: payload.isAuth,
      };
    }
    case "LOGOUT": {
      return initialAuthState;
    }
    default: {
      throw new Error("Illegal action");
    }
  }
};
