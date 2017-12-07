import com.quantego.clp.CLP;
import com.quantego.clp.CLPVariable;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * Created by Szymon on 06/12/2017.
 */
public class Main {

    public static void main(String[] args)

        {

            System.out.println("Test");

            Model model = new Model( "-queens problem");


            IntVar x1 = model.intVar("x1", IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);
            IntVar x2 = model.intVar("x2", IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);
            IntVar x3 = model.intVar("x3", IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);


            model.arithm(x1, "<", x2).post();
            model.arithm(x2, "<", x3).post();
            model.arithm(x3, "<", x1).post();

            try {
                Solver solver =  model.getSolver();
               solver.propagate();
            } catch (ContradictionException e) {
                e.printStackTrace();
            }


//            CLP solver = new com.quantego.clp.CLP().buffer(2).maximization();
//            CLPVariable x0 = solver.addVariable().ub(1.0);
//            CLPVariable x1 = solver.addVariable().ub(0.3).obj(2.655523).name("var_1");
//            CLPVariable x2 = solver.addVariable().ub(0.3).obj(-2.70917);
//            CLPVariable x3 = solver.addVariable().free().obj(1);
//            solver.createExpression().add(-2,x0).add(-1.484345,x0).add(x3).leq(0.302499);
//            solver.createExpression().add(-3.074807,x0).add(x3).leq(0.507194);
//            solver.createExpression().add(x0).add(1.01,x1).add(-.99,x2).eq(0.594).name("eq_ctr");
//
//            System.out.println(solver.toString());
//            CLP solver = new com.quantego.clp.CLP().buffer(2).maximization();
//            CLPVariable x0 = solver.addVariable().ub(1).lb(0).obj(1);
//            CLPVariable x1 = solver.addVariable().ub(1).lb(0);
//           // solver.createExpression().add(1.0,x0).add(-1.0, x1).leq(0.0);
//           // solver.createExpression().add(1.0,x1).add(-1.0, x0).leq(0.0);
//            solver.createExpression().add(x0).add(x1).neq(0.0);
//
//            System.out.println(solver.toString());
//
//            CLP.STATUS res = solver.solve();
//
//            System.out.println(solver.getSolution(x0));
//            System.out.println(solver.getSolution(x1));
//            System.out.println(res.toString());

            String duration1 = "P17Y";
     //       parse("2002-10-10T12:00:00-05:00");
            String date1 = "2017-01-01";

            Period period = Period.parse(duration1, ISOPeriodFormat.standard());

            DateTime mydate1 = DateTime.parse(date1, ISODateTimeFormat.date());

            Duration duration = period.toDurationFrom(new DateTime());
//
            long retval = duration.getMillis() / 1000 / 60 / 60;
            System.out.println(mydate1.getMillis() /1000 / 60 / 60);
            System.out.println(((mydate1.plusDays(1)).getMillis() - mydate1.getMillis()) /1000  );
            System.out.println(retval);


        }

        private static final DateTimeFormatter XML_DATE_TIME_FORMAT =

                ISODateTimeFormat.date();
               // ISODateTimeFormat.dateTimeNoMillis();

        private static final DateTimeFormatter CHECKING_FORMAT =
                ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC);

        static void parse(String text)
        {
            System.out.println("Parsing: " + text);
            DateTime dt = XML_DATE_TIME_FORMAT.parseDateTime(text);
            System.out.println("Parsed to: " + CHECKING_FORMAT.print(dt));
        }

}
