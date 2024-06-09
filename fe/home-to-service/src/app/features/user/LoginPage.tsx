import axios from "axios";
import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import { Link, useNavigate } from "react-router-dom";
import { useStore } from "../../stores/stores";
import { useCookies } from "react-cookie";

export default function LoginPage() {
  const navigation = useNavigate();
  const { userStore } = useStore();
  const [cookies, setCookie] = useCookies(["auth"]);

  useEffect(() => {
    if (userStore.isLoggedIn) navigation("/not-found");
  }, []);

  const [input, setInput] = useState<LoginInput>({
    email: "",
    password: "",
  });

  const handleLogin = async () => {
    if (
      Object.values(input).some((value) => value.length < 3) ||
      !input.email.includes("@") ||
      input.password.length < 6
    ) {
      toast.error("All the field are required!");
      return;
    }

    try {
      var response = await axios.post<LoginResponse>(
        "http://localhost:8080/api/users/login",
        input,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      );

      if (response.data.message) {
        toast.error(response.data.message);
      } else {
        userStore.setToken(response.data.jwt!);
        setCookie("auth", response.data.jwt, { path: "/" });
        toast.success("Logged in successfully!");
        navigation("/");
        window.location.reload();
      }
    } catch (error) {
      toast.error("Invalid credentials!");
    }
  };

  return (
    <section className="my-4">
      <div className="mask d-flex align-items-center">
        <div className="container h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-9 col-lg-7 col-xl-6">
              <div className="card">
                <div className="card-body p-5">
                  <h2 className="text-uppercase text-center mb-5">Login</h2>

                  <form>
                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Email</label>
                      <input
                        type="email"
                        className="form-control form-control-lg"
                        value={input.email}
                        onChange={(e) =>
                          setInput({ ...input, email: e.target.value })
                        }
                      />
                    </div>

                    <div data-mdb-input-init className="form-outline mb-4">
                      <label className="form-label">Password</label>
                      <input
                        type="password"
                        className="form-control form-control-lg"
                        value={input.password}
                        onChange={(e) =>
                          setInput({ ...input, password: e.target.value })
                        }
                      />
                    </div>

                    <div className="d-flex justify-content-center">
                      <button
                        onClick={handleLogin}
                        type="button"
                        className="btn btn-primary btn-block btn-lg"
                      >
                        Login
                      </button>
                    </div>

                    <p className="text-center mt-5 mb-0">
                      Don't have an account?{" "}
                      <Link
                        to={`/user/register`}
                        className="fw-bold text-success text-decoration-none"
                      >
                        Register here{" "}
                      </Link>
                    </p>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
