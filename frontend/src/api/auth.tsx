const HOST = import.meta.env.VITE_HOST;
const PORT = import.meta.env.VITE_PORT;

type loginCredentials = {
  username: string;
  password: string;
};

type signUpCredentials = {
  username: string;
  password: string;
  name: string;
  role: string;
};

type loginResponse = {
  username: string;
  name: string;
  roles: string[];
  accessToken: string;
};

export const login = async (
  credentials: loginCredentials
): Promise<loginResponse | null> => {
  try {
    const res = await fetch(`${HOST}:${PORT}/api/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credentials),
    });

    if (res.ok) {
      const data = await res.json();
      return data;
    } else {
      throw new Error("Login failed");
    }
  } catch (e) {
    console.error(e);
    return null;
  }
};

export const signUp = async (
  credentials: signUpCredentials
): Promise<boolean> => {
  try {
    const res = await fetch(`${HOST}:${PORT}/api/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credentials),
    });
    return res.ok ? true : false;
  } catch (e) {
    console.error(e);
    return false;
  }
};
