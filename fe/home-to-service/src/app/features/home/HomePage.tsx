import React from "react";
import { Link } from "react-router-dom";
import { useStore } from "../../stores/stores";

const HomePage: React.FC = () => {
  const { userStore } = useStore();

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 text-center">
          <h1 className="display-4 mt-5">Welcome to Home To Service</h1>
          <p className="lead mt-3">
            Create and discover posts for different types of services in various
            categories.
          </p>
          {!userStore.isLoggedIn && (
            <div className="mt-5">
              <Link to="/login" className="btn btn-primary mr-3">
                Login
              </Link>
              <Link to="/register" className="btn btn-success">
                Register
              </Link>
            </div>
          )}
          <div className="mt-5">
            <h2>Popular Categories</h2>
            <ul className="list-group">
              <li className="list-group-item">House Cleaning</li>
              <li className="list-group-item">Lawn Care</li>
              <li className="list-group-item">Plumbing</li>
              <li className="list-group-item">Electrical</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
