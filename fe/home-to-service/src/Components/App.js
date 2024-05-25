import React, {createContext, useState} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import MyNavbar from "./MyNavbar";
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from "./Footer";
import CreatePostPage from "./CreatePostPage";
import HomePage from "./HomePage";
import ContactsPage from "./ContactsPage";
import CategoriesList from "./CategoriesList";
import PostsForCategory from "./PostsForCategory";
//import AllPostsPage from "./AllPostsPage";

const defaultUser = {'userId': 1, 'userType': 'admin', 'username': 'MyUser'};
export const UserContext = createContext(null);

function App() {
  const [user, setUser]= useState(defaultUser);
  //const UserContext = createContext(user);

  function login(response){
    setUser(response.user);
  }

  return (
    <UserContext.Provider value={{user, login}}>
    <Router>
      <div className="App">
        <MyNavbar />
        <header className="App-header">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/index.html" element={<HomePage />} />
            <Route path="/home" element={<HomePage />} />
            <Route path="/post/create" element={<CreatePostPage />} />
            <Route path="contacts" element={<ContactsPage />} />
            <Route path="/categories-list" element={<CategoriesList />} />
            <Route path="/posts/category/:categoryId" element={<PostsForCategory />} />
            {/*<Route path="/all-posts" element={<AllPostsPage />} />*/}
          </Routes>
        </header>
        <Footer />
      </div>
    </Router>
    </UserContext.Provider>
  );
}

export default App;