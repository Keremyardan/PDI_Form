export const LogOut = () => {
  localStorage.removeItem("auth");
  window.location.href = "/login";
};


