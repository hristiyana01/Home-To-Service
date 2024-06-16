import React from "react";
import { Link } from "react-router-dom";

export default function AboutPage() {
  return (
    <div className="container about-page">
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>About HomeToService</h1>
          <p className="lead">
            Welcome to HomeToService, your one-stop solution for all home
            service needs. Our platform connects homeowners with professional
            service providers to ensure your home is well-maintained and
            comfortable.
          </p>
        </div>
      </div>

      <div className="row mt-5">
        <div className="col-md-6">
          <h2>Our Mission</h2>
          <p>
            Our mission is to make home services easily accessible and reliable.
            We aim to provide a seamless experience for both homeowners and
            service providers, ensuring high-quality services and customer
            satisfaction.
          </p>
        </div>
        <div className="col-md-6">
          <img
            src="public/our-mission.png"
            alt="About Us Image"
            className="img-fluid"
          />
        </div>
      </div>

      <div className="row mt-5">
        <div className="col-md-12">
          <h2>What We Offer</h2>
          <ul className="list-group">
            <li className="list-group-item">
              Professional home cleaning services
            </li>
            <li className="list-group-item">
              Expert plumbing and electrical services
            </li>
            <li className="list-group-item">
              Home renovation and repair services
            </li>
            <li className="list-group-item">
              Appliance installation and repair
            </li>
            <li className="list-group-item">
              Gardening and landscaping services
            </li>
            <li className="list-group-item">And much more...</li>
          </ul>
        </div>
      </div>

      <div className="row mt-5">
        <div className="col-md-12">
          <h2>Why Choose Us?</h2>
          <ul className="list-group">
            <li className="list-group-item">
              Vetted and experienced professionals
            </li>
            <li className="list-group-item">Easy booking and scheduling</li>
            <li className="list-group-item">Competitive pricing</li>
            <li className="list-group-item">
              Customer support and satisfaction guarantee
            </li>
            <li className="list-group-item">User-friendly platform</li>
          </ul>
        </div>
      </div>

      <div className="row mt-5">
        <div className="col-md-12 text-center">
          <h2>Get in Touch</h2>
          <p>
            If you have any questions or need further assistance, feel free to
            reach out to our customer support team. We are here to help you with
            all your home service needs.
          </p>
          <button className="btn btn-primary mb-3">
            <Link to="/contacts" className="text-white text-decoration-none">
              Contact Us
            </Link>
          </button>
        </div>
      </div>
    </div>
  );
}
