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
              <Link to="user/login" className="btn btn-primary me-2">
                Login
              </Link>

              <Link to="user/register" className="btn btn-success">
                Register
              </Link>
            </div>
          )}
          <div className="mt-5">
            <h2>Popular Categories</h2>
            <ul className="list-group">
              <a className="text-decoration-none" href="/categories">
                <li className="list-group-item">House Cleaning</li>
                <li className="list-group-item">Lawn Care</li>
                <li className="list-group-item">Plumbing</li>
                <li className="list-group-item">Electrical</li>
                <li className="list-group-item">Waterproofing</li>
                <li className="list-group-item">Snow Removal</li>
                <li className="list-group-item">Fencing</li>
                <li className="list-group-item">Deck Building</li>
                <li className="list-group-item">Solar Panel Installation</li>
                <li className="list-group-item">Septic System Maintenance</li>
                <li className="list-group-item">Tree Trimming</li>
                <li className="list-group-item">
                  Sprinkler System Installation
                </li>
                <li className="list-group-item">Garage Door Repair</li>
                <li className="list-group-item">Exterior Cleaning</li>
                <li className="list-group-item">Drywall Repair</li>
                <li className="list-group-item">Cabinet Making</li>
                <li className="list-group-item">Tile and Grout Cleaning</li>
                <li className="list-group-item">Insulation Services</li>
                <li className="list-group-item">Chimney Sweep</li>
                <li className="list-group-item">Masonry</li>
                <li className="list-group-item">Home Energy Audit</li>
                <li className="list-group-item">Basement Waterproofing</li>
                <li className="list-group-item">Garage Organization</li>
                <li className="list-group-item">Home Theater Installation</li>
                <li className="list-group-item">Smart Home Installation</li>
                <li className="list-group-item">
                  Window Treatment Installation
                </li>
                <li className="list-group-item">Home Staging</li>
                <li className="list-group-item">Power Washing</li>
              </a>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
