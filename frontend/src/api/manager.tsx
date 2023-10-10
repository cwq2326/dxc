const HOST = import.meta.env.VITE_HOST;
const PORT = import.meta.env.VITE_PORT;

type Content = {
  content: string;
};

export const getManagerContent = async (
  token: string
): Promise<Content | null> => {
  try {
    const res = await fetch(`${HOST}:${PORT}/api/manager/document`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (res.ok) {
      const data = res.json();
      return data;
    } else {
      throw new Error("Cannot get manager document");
    }
  } catch (e) {
    console.error(e);
    return null;
  }
};
