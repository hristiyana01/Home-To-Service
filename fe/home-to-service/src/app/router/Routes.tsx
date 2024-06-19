import { RouteObject, createBrowserRouter } from "react-router-dom";
import App from "../layout/App";
import HomePage from "../features/home/HomePage";
import ErrorPage from "../layout/ErrorPage";
import CreatePostPage from "../features/posts/CreatePostPage";
import RegisterPage from "../features/user/RegisterPage";
import LoginPage from "../features/user/LoginPage";
import ContactsPage from "../features/contacts/ContactsPage";
import PostsPage from "../features/posts/PostsPage";
import CategoriesPage from "../features/categories/CategoriesPage";
import UserDetailsPage from "../features/user/UserDetails";
import PostDetailsPage from "../features/posts/PostDetailsPage";
import EditPostPage from "../features/posts/PostEditPage";
import PostUserDetails from "../features/user/PostUserDetails";
import PostsForCategory from "../features/posts/PostsForCategoryPage";
import AboutPage from "../features/about/AboutPage";
        import FavoritesPage from "../features/user/FavouritesPage";

export const routes: RouteObject[] = [
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        index: true,
        path: "",
        element: <HomePage />,
      },
      {
        path: "/about",
        element: <AboutPage />,
      },
      {
        path: "/posts/:postId",
        element: <PostDetailsPage />,
      },
      {
        path: "/posts/create",
        element: <CreatePostPage />,
      },
      {
        path: "/contacts",
        element: <ContactsPage />,
      },
      {
        path: "/categories",
        element: <CategoriesPage />,
      },
      {
        path: "/profile",
        element: <UserDetailsPage />,
      },
      {
        path: "/profile/:userId",
        element: <PostUserDetails />,
      },

      {
        path: "user",
        children: [
          {
            path: "login",
            element: <LoginPage />,
          },
          {
            path: "register",
            element: <RegisterPage />,
          },
        {
        path: "favorites",
        element: <FavoritesPage/>,
        },
        ],
      },
      {
        path: "posts",
        children: [
          {
            path: "all",
            element: <PostsPage />,
          },
          {
            path: "create",
            element: <CreatePostPage />,
          },
          {
            path: "edit/:postId",
            element: <EditPostPage />,
          },
          {
            path: "category/:categoryId",
            element: <PostsForCategory />,
          },
        ],
      },
    ],
  },
];

export const router = createBrowserRouter(routes);
