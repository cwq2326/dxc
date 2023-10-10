import { Box, Button, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import useAuth from "../context/AuthContext";

export default () => {
  const { username, name, roles, removeAuth } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    removeAuth();
    navigate("/login");
  };

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
      <Typography variant="h1" sx={{ paddingBottom: "5rem" }}>
        Welcome
      </Typography>
      <Typography variant="h2">Name: {name}</Typography>
      <Typography variant="h2">Username: {username}</Typography>
      <Typography variant="h2">Role: {roles}</Typography>
      {roles.includes("MANAGER") && (
        <Button
          sx={{ bgcolor: "white" }}
          onClick={() => {
            navigate("/manager");
          }}
        >
          ACCESS RESTRICTED WEBPAGE
        </Button>
      )}
      <Button onClick={handleLogout}>LOGOUT</Button>
    </Box>
  );
};
