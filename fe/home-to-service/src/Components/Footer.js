import { Container, Row, Col } from 'react-bootstrap';

function Footer() {
  return (
    <footer className="py-5 bg-dark">
      <Container>
        <Row>
          <Col className="m-0 text-center text-white">
            Home To Service © {new Date().getFullYear()} | Developed by Hristiyana
          </Col>
        </Row>
        <Row>
          <Col className="m-0 text-center text-white">
            Technical University
          </Col>
        </Row>
      </Container>
    </footer>
  );
}

export default Footer;