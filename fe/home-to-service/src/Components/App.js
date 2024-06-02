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
import DetailedPostView from "./DetailedPostView";
import DetailedUserView from './DetailedUserView'; // Import DetailedUserView component
import Login from './Login';
import AllPostsPage from './AllPostsPage';
//import AllPostsPage from "./AllPostsPage";

const defaultUser = {'id': 1, 'userType': 'admin', 'username': 'MyUser'};
export const UserContext = createContext();

function App() {
  const [user, setUser]= useState(defaultUser);
  //const UserContext = createContext(user);
//  const user = { id: 1 }; // Mocked user data, replace with actual user data

  function login(response){
    setUser(response.user);
  }
    // images upload - https://www.youtube.com/watch?v=3f5Q9wDePzY
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
            <Route path="/posts/:postId" element={<DetailedPostView />} />
            <Route path="/users/:userId" element={<DetailedUserView />} />
            <Route path="/login" element={<Login />} />
            <Route path="/all-posts" element={<AllPostsPage />} />
          
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