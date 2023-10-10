import React from "react";
import useAuth from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import { Box } from "@mui/material";
import { getManagerContent } from "../api/manager";

export default () => {
  const [content, setContent] = React.useState<string>();
  const { token } = useAuth();
  React.useEffect(() => {
    (async () => {
      try {
        const data = await getManagerContent(token);
        if (data !== null) {
          setContent(data.content);
        } else {
          throw new Error("User does not have MANAGER role");
        }
      } catch (e) {
        console.error(e);
      }
    })();
  });

  return (
    <Box
      sx={{
        height: "100vh",
        width: "100vw",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <h1>RESTRICTED WEB PAGE FOR MANAGER ONLY</h1>
      <p>RESTRICTED CONTENT: {content}</p>
    </Box>
  );
};
