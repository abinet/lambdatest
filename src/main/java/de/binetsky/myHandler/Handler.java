package de.binetsky.myHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.source;
import static org.jsoup.Jsoup.parse;

public class Handler implements RequestHandler<S3Event, String> {
	@Override
	public String handleRequest(S3Event event, Context context) {
		open("http://www.4konverta.com");
		$(byClassName("login")).click();
		$(byId("login")).setValue(System.getProperty("4konverta.login"));
		$(byId("password")).setValue(System.getProperty("4konverta.password"));
		$(byXpath("//button[@type='submit']")).click();
		$(byLinkText("Все цели")).click();

		final Document document = parse(source());
		final Elements elements = document.select("div.ui-corner-all");

		elements.stream()
				.filter(e -> !StringUtil.isBlank(e.select("h2").text()))
				.forEach(e ->
					System.out.println(new  JsonTarget(new Target(e.select("h2").text(), e.select("td").text())).toJson())
				);


		return "Done executing handler";
	}

}
