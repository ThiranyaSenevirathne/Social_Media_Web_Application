import React from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import Layout from "../layout/Layout";
import SignIn from "../pages/auth/SignIn";
import SignUp from "../pages/auth/SignUp";
import Followers from "../pages/followers/Followers";
import Home from "../pages/home/Home";
import MyProfile from "../pages/myprofile/MyProfile";
import Posts from "../pages/posts/Posts";
import Search from "../pages/search/Search";


function routes() {
  return (
    <Routes>
      {/* Authentication Route */}
      <Route path="/" element={<Navigate to="/sign-in" />} />
      <Route path="/sign-in" element={<SignIn />} />
      <Route path="/sign-up" element={<SignUp />} />

      {/* User Route */}
      <Route path="/user" element={<Layout />}>
        <Route path="/user/home" element={<Home />} />
        <Route path="/user/followers" element={<Followers />} />
        <Route path="/user/posts" element={<Posts />} />
        <Route path="/user/myprofile" element={<MyProfile />} />
        <Route path="/user/search" element={<Search />} />
        <Route path="/user/logout" />
      </Route>
    </Routes>
  );
}

export default routes;
